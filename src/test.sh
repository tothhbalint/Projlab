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
        \n      11. Water flow" #done

  read input
  #add grep at the end of the lines, compare with outputs from a file
  case $input in
  1)
    java Proto -t step -plumber0 -pipe0 >test1.txt
    cat test1.txt
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
    echo "oil -nomad1 step -nomad1 -p1 step -plumber1 -p0" | $PROTO | grep "player accepted"
    if [ $? -eq 1 ]; then
      echo "Success"
    else
      echo "Failed"
    fi
    ;;
  4) echo "pickup -plumber1 -pump" | $PROTO ;;
  5) echo "place -plumber1 -pipe" | $PROTO ;;
  6) echo "break -nomad1" | $PROTO ;;
  7) echo "fix -player1" | $PROTO ;;
  8) echo "place -plumber1 -pump" | $PROTO ;;
  9) echo "oil -nomad1" | $PROTO ;;
  10) echo "glue -player1" | $PROTO ;;
  11)
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
  esac
  echo "Press any key to continue..."
  read go_next
done
