#!/bin/sh

PROTO="java Proto -t"

eval javac *.java

while :; do
  echo "Choose which test to run
        \n      0. Run all tests
        \n      1. Movement() #done
        \n      2. Movement(occupied pipe) #done
        \n      3. Movement(slippy pipe)
        \n      4. Movement(sticky pipe)
        \n      5. Pump pickup
        \n      6. Pipe pickup
        \n      7. Pump placement
        \n      8. Break pipe
        \n      9. Fix
        \n      10. Connect pipe
        \n      11. Water flow
        \n      12. Points added to plumber team
        \n      13. Points added to nomad team" #done

  read input
  #add grep at the end of the lines, compare with outputs from a file
  case $input in
  1)
    java Proto -t step -plumber0 -pipe0 >test1.txt
    cat test1.txt; echo " ";
    grep "player accepted" <test1.txt
    if test "$(grep "player accepted" <test1.txt | wc -l )" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  2) java Proto -t step -plumber1 -pump0 >test2.txt
    cat test2.txt; echo " ";
    grep "player rejected" <test2.txt
    grep "pipe occupied" <test2.txt
    if test "$(grep "pipe occupied" <test2.txt | wc -l)" -eq 1 &&
        test "$(grep "player rejected" <test2.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  3)
    java Proto -t oil -nomad1 step -nomad1 -p1 step -plumber1 -p0 >test3.txt
    cat test3.txt; echo " ";
    grep "player slipped" <test3.txt
    grep "player accepted" <test3.txt

    if test "$(grep "player slipped" <test3.txt | wc -l)" -eq 1 &&
        test "$(grep "player accepted" <test3.txt | wc -l)" -eq 4; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  4)
    java Proto -t glue -nomad1 step -nomad1 -p1 step -plumber1 -p0 step -plumber1 -p0 >test4.txt
    cat test4.txt; echo " ";
    grep "player is stuck" <test4.txt
    grep "player accepted" <test4.txt
    if test "$(grep "player is stuck" <test4.txt | wc -l)" -eq 1 &&
        test "$(grep "player accepted" <test4.txt | wc -l)" -eq 4; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  5)
    java Proto -t pickup -plumber0 -pump step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 pickup -plumber0 -pump>test5.txt
    cat test5.txt; echo " ";
    grep "pickup failed" <test5.txt
    grep "pump added to the inventory" <test5.txt
    if test "$(grep "pickup failed" <test5.txt | wc -l)" -eq 1 &&
        test "$(grep "pump added to the inventory" <test5.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  6) #pipe pickup
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 pickup -plumber0 -pipe
    cat test6.txt; echo " ";
    grep "pipe added to the inventory" <test6.txt
    if test "$(grep "pipe added to the inventory" <test6.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  7) #pump placement
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 pickup -plumber0 -pump step -plumber0 -p0 place -plumber0 -pump >test7.txt
    cat test7.txt; echo " ";
    grep "pump added to the inventory" <test7.txt
    grep "pump placed" <test7.txt
    if test "$(grep "pump added to the inventory" <test7.txt | wc -l)" -eq 1 &&
        test "$(grep "pump placed" <test7.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  8) #break
    java Proto -t break -nomad1 > test8.txt
    cat test8.txt; echo " ";
    grep "pipe broken" <test8.txt
    if test "$(grep "pipe broken" <test8.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  9) #fix
    java Proto -t break -nomad1 step -nomad1 -p0 step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p2 step -plumber0 -p1 step -plumber0 -p0 fix -plumber0 >test9.txt
    cat test9.txt; echo " ";
    if test "$(grep "pipe fixed" <test9.txt | wc -l)" -eq 1 &&
        test "$(grep "pipe broken" <test9.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  11) #water transfer
    java Proto -t flow>test13.txt
    cat test13.txt
    grep "Water state set to true" test13.txt
    grep "cistern.receiveWater" test13.txt
    if test "$(grep "Water state set to true" test13.txt | wc -l)" -eq 9 &&
       test "$(grep "cistern.receiveWater" test13.txt | wc -l)" -eq 3; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  12) #Points added to Plumber's score
    java Proto -t flow flow flow flow flow flow >test12.txt # 6 flow should transfer the water to the cistern
    cat test12.txt
    grep "Plumber's score increased" test12.txt
    if test "$(grep "Plumber's score increased" test12.txt | wc -l)" -eq 3; then # All 3 basic cisterns should get water once
      echo "Success"
    else
      echo "Failed"
    fi

    ;;
  13) #Points added to Nomad's score
    # Here I wanted to break the first pipe, at bottom-left.
    # That should add 5 points to Nomad's score (1: transfer water to pipe, 2-6: points added)
    # The other two cisterns should get 1-1 point each.
    java Proto -t break -nomad1 flow flow flow flow flow flow >test13.txt # 6 flow should transfer the water to the cistern and pipe12 (id 17) should break
    cat test13.txt
    grep "Nomad's score increased" test13.txt
    if test "$(grep "Nomad's score increased" test13.txt | wc -l)" -eq 5 &&
        test "$(grep "Plumber's score increased" test13.txt | wc -l)" -eq 2; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  esac
  echo "Press any key to continue..."
  read go_next
done
