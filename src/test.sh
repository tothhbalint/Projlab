#!/bin/sh

PROTO="java Proto -t"

eval javac *.java

while :
do
        echo "Choose which test to run
        \n      0. Run all tests
        \n      1. Movement(pipe)
        \n      2. Movement(slippy pipe)
        \n      3. Movement(sticky pipe)
        \n      4. Movement(pump)
        \n      5. Pump pickup
        \n      6. Pipe pickup
        \n      7. Pump placement
        \n      8. Break pipe
        \n      9. Fix
        \n      10. Connect pipe
        \n      11. Slippy pipe
        \n      12. Sticky pipe
        \n      13. Water flow"

        read input
        #add grep at the end of the lines, compare with outputs from a file
        case $input in
                0)java Proto -t step -plumber0 -pipe0 > test1.txt;
                  cat test1.txt;
                  grep "player accepted" <test1.txt;
		  if test "$( wc -l < test1.txt)" -eq 1; then
			  echo "Success"
		  else
			  echo "Failed"
		  fi;;
                2) echo "step -player1 -pump1" | $PROTO;;
                3) echo "oil -nomad1 step -nomad1 -p1 step -plumber1 -p0" | $PROTO | grep "player accepted"
                  if [ $? -eq 1 ];then
                        echo "Success"
                else
                        echo "Failed"
                fi;;
                4) echo "pickup -plumber1 -pump" | $PROTO;;
                5) echo "place -plumber1 -pipe" | $PROTO;;
                6) echo "break -nomad1"  | $PROTO;;
                7) echo "fix -player1"  | $PROTO;;
                8) echo "place -plumber1 -pump" | $PROTO;;
                9) echo "oil -nomad1"  | $PROTO;;
                11) echo "glue -player1"  | $PROTO;;
                12) java Proto -t flow > test13.txt;
		       cat test13.txt;
		       grep "Water state set to true" <test13.txt;
		  if test "$( wc -l < test13.txt)" -eq 13; then
			  echo "Success"
		  else
			  echo "Failed"
		  fi;;
        esac
        echo "Press any key to continue..."
        read go_next
done

