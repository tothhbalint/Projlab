#!/bin/sh

PROTO="java Proto -t"

eval javac *.java

while :; do
  echo "Choose which test to run
        \n      0. Run all tests
        \n      1. Movement(pipe) #done
        \n      2. Movement(occupied pipe) #done
        \n      3. Movement (pump) #done
        \n      4. Movement (cistern)
        \n      5. Movement (source)
        \n      6. Slippery pipe
        \n      7. Sticky pipe
        \n      8. Slippery pipe on sticky pipe
        \n      9. Movement(slippery pipe)
        \n      10. Movement(sticky pipe)
        \n      11. Pump pickup
        \n      12. Pump pickup at invalid location
        \n      13. Pipe pickup
        \n      14. Inventory is full (Pipe)
        \n      15. Inventory is full (Pump)
        \n      16. Pump placement
        \n      17. Break pipe
        \n      18. Fix pipe
        \n      19. Connect pipe
        \n      20. Water flows from cistern to pipe
        \n      21. Water flows from pipe to Pump
        \n      22. Water flows from pump to pipe
        \n      23. Water flows from pipe to cistern
        \n      24. Points added to plumber team
        \n      25. Points added to nomad team"

  read input
  #add grep at the end of the lines, compare with outputs from a file
  case $input in
  1) #Movement (pipe)
    java Proto -t step -plumber0 -pipe0 >test1.txt
    cat test1.txt; echo " ";
    grep "player accepted" <test1.txt
    if test "$(grep "player accepted" <test1.txt | wc -l )" -eq 3; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  2) #Movement (occupied pipe)
    java Proto -t step -plumber0 -p0 step -nomad1 -p1 step -nomad1 -p1 step -nomad1 -p0 step -nomad1 -p0 >test2.txt
    cat test2.txt; echo " ";
    grep "player rejected" <test2.txt
    grep "pipe occupied" <test2.txt
    if test "$(grep "pipe occupied" <test2.txt | wc -l)" -eq 1 &&
        test "$(grep "player rejected" <test2.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  3) #Movement(pump)
        java Proto -t step -plumber0 -p0 step -plumber0 -p1 >test3.txt
        cat test3.txt; echo " ";
        grep "player accepted" <test3.txt
        if test "$(grep "player accepted" <test3.txt | wc -l )" -eq 2; then
          echo "Success"
        else
          echo "Failed"
        fi
        ;;
  4) #Movement(cistern)
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1  >test4.txt
    cat test4.txt; echo " ";
    grep "player accepted" <test4.txt
    if test "$(grep "player accepted" <test4.txt | wc -l )" -eq 6; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  5) #Movement(source)
    java Proto -t step -plumber0 -p0 step -plumber0 -p0 > test5.txt
    cat test5.txt;
    grep "player accepted" <test5.txt
    if test "$(grep "player accepted" <test5.txt | wc -l )" -eq 2; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  6) #Slippery pipe
    java Proto -t oil -nomad1 > test6.txt
    cat test6.txt;
    grep "Nomad.makePipeSlippery()" < test6.txt
    grep "pipe now slippery" < test6.txt
    if test "$(grep "pipe now slippery" <test6.txt | wc -l )" -eq 1; then
        #test "$(grep "Nomad.makePipeSlippery()" < test6.txt | wc -l )" -eq 1; then #maybe we dont need this
        echo "Success"
    else
      echo "Failed"
    fi
    ;;
  7) #Sticky pipe
    java Proto -t glue -nomad1 > test7.txt
    cat test7.txt;
    grep "pipe now sticky" < test7.txt
    if test "$(grep "pipe now sticky" <test7.txt | wc -l )" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  8) #Slippery pipe on sticky pipe
    java Proto -t glue -nomad1 oil -nomad1 > test8.txt
    cat test8.txt;
    grep "Error: Pipe sticky AND slippery" < test8.txt
    if test "$(grep "Error: Pipe sticky AND slippery" <test8.txt | wc -l )" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  9) # Movement(slippery pipe)
    java Proto -t oil -nomad1 step -nomad1 -p1 step -plumber1 -p0 >test9.txt
    cat test9.txt; echo " ";
    grep "player slipped" <test9.txt
    grep "player accepted" <test9.txt

    if test "$(grep "player slipped" <test9.txt | wc -l)" -eq 1 &&
        test "$(grep "player accepted" <test9.txt | wc -l)" -eq 2; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  10) #Movement(sticky pipe)
    java Proto -t glue -nomad1 step -nomad1 -p1 step -plumber1 -p0 step -plumber1 -p0 >test10.txt
    cat test10.txt; echo " ";
    grep "player stuck" <test10.txt
    grep "player accepted" <test10.txt
    if test "$(grep "player stuck" <test10.txt | wc -l)" -eq 1 &&
        test "$(grep "player accepted" <test10.txt | wc -l)" -eq 2; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  11) #Pump pickup
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 pickup -plumber0 -pump>test11.txt
    cat test11.txt; echo " ";
    grep "pump added" <test11.txt
    if  test "$(grep "pump added to the inventory" <test11.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  12) #Pump pickup at invalid location
    java Proto -t pickup -plumber0 -pump > test12.txt
    cat test12.txt; echo " ";
    grep "pump added" <test12.txt
    if test "$(grep "pump added" <test12.txt | wc -l)" -eq 0; then
      echo "Success"
    else
      echo "Failed"
    fi ;;
  13) #pipe pickup
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 pickup -plumber0 -pipe > test13.txt
    cat test13.txt; echo " ";
    grep "pipe added" <test13.txt
    if test "$(grep "pipe added" <test13.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  14) #Inventory is full (pipe)
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 pickup -plumber0 -pipe pickup -plumber0 -pipe > test13.txt
    cat test13.txt; echo " ";
    grep "pipe added" <test13.txt
    grep "inventory is full" <test13.txt
    if test "$(grep "inventory is full" <test13.txt | wc -l)" -eq 1 &&
      test "$(grep "pipe added" <test13.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  15) #Inventory is full (pump)
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 pickup -plumber0 -pump pickup -plumber0 -pump > test14.txt
    cat test14.txt; echo " ";
    grep "pump added" <test14.txt
    grep "pump not added : inventory full" <test14.txt
    if test "$(grep "pump not added : inventory full" <test14.txt | wc -l)" -eq 1 &&
      test "$(grep "pump added" <test14.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  16) #pump placement
    java Proto -t step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 step -plumber0 -p1 pickup -plumber0 -pump step -plumber0 -p0 place -plumber0 -pump >test16.txt
    cat test16.txt; echo " ";
    grep "pump added to the inventory" <test16.txt
    grep "pump placed" <test16.txt
    if test "$(grep "pump added to the inventory" <test16.txt | wc -l)" -eq 1 &&
        test "$(grep "pump placed" <test16.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  17) #break pipe
    java Proto -t break -nomad1 > test17.txt
    cat test17.txt; echo " ";
    grep "pipe broken" <test17.txt
    if test "$(grep "pipe broken" <test17.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  18) #fix pipe
    java Proto -t break -nomad1 step -nomad1 -p0 step -plumber0 -p0 step -plumber0 -p1 step -plumber0 -p2 step -plumber0 -p1 step -plumber0 -p0 fix -plumber0 >test18.txt
    cat test18.txt; echo " ";
    grep "pipe repaired" <test18.txt
    grep "pipe broken" <test18.txt
    if test "$(grep "pipe repaired" <test18.txt | wc -l)" -eq 1 &&
        test "$(grep "pipe broken" <test18.txt | wc -l)" -eq 1; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  19) #Connect pipe

    ;;
  20) # Water flows from source to pipe
    java Proto -t flow>test20.txt
    cat test20.txt
    grep "Water state set to true" test20.txt
    grep "pipe.receiveWater" test20.txt
    if test "$(grep "pipe.receiveWater" test20.txt | wc -l)" -eq 3; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  21) # Water flows from pipe to pump
    java Proto -t flow flow >test21.txt
    cat test21.txt
    grep "Water state set to true" test21.txt
    grep "pump.receiveWater" test21.txt
    if test "$(grep "pump.receiveWater" test21.txt | wc -l)" -eq 3; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  22) # Water flows from pump to pipe
    java Proto -t flow flow flow >test22.txt
    cat test22.txt
    grep "Water state set to true" test22.txt
    grep "pipe.receiveWater" test22.txt
    if test "$(grep "pipe.receiveWater" test22.txt | wc -l)" -eq 12; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  23) # Water flows from pipe to cistern
    java Proto -t flow flow flow flow flow flow>test23.txt
    cat test23.txt
    grep "cistern.receiveWater" test23.txt
    if test "$(grep "cistern.receiveWater" test23.txt | wc -l)" -eq 3; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  24) #Points added to Plumber's score
    java Proto -t flow flow flow flow flow flow>test24.txt # 6 flow should transfer the water to the cistern
    cat test24.txt
    grep "Plumber points increased" test24.txt
    if test "$(grep "Plumber points increased" test24.txt | wc -l)" -eq 3; then # All 3 basic cisterns should get water once
      echo "Success"
    else
      echo "Failed"
    fi

    ;;
  25) #Points added to Nomad's score
    # Here I wanted to break the first pipe, at bottom-left.
    # That should add 5 points to Nomad's score (1: transfer water to pipe, 2-6: points added)
    # The other two cisterns should get 1-1 point each.
    java Proto -t break -nomad1 flow flow flow flow flow flow >test25.txt # 6 flow should transfer the water to the cistern and pipe12 (id 17) should break
    cat test25.txt
    grep "Nomad points increased" test25.txt
    grep "Plumber points increased" test25.txt
    if test "$(grep "Nomad points increased" test25.txt | wc -l)" -eq 6 &&
        test "$(grep "Plumber points increased" test25.txt | wc -l)" -eq 2; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  esac
  echo "Press any key to continue..."
  read go_next
done
