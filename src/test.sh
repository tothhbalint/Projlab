#!/bin/sh

PROTO="java Proto"

while :
do
	echo "Choose which test to run
	\n	0. Run all tests
	\n	1. Movement(pipe)
	\n	2. Movement(pump)
	\n	3. Pump pickup
	\n	4. Pipe pickup
	\n	5. Pump placement
	\n	6. Break pipe
	\n	7. Fix
	\n	8. Connect pipe
	\n	9. Slippy pipe
	\n	10. Sticky pipe
	\n	11. Water flow"

	read inpu
	#add grep at the end of the lines, compare with outputs from a file
	case $input in 
		0) echo -a |  $PROTO;; 
		1) echo "step -player1 -pipe1" | $PROTO;;
		2) echo "step -player1 -pump1" | $PROTO;;
		3) echo "pickup -plumber1 -pipe" | $PROTO;;
		4) echo "pickup -plumber1 -pump" | $PROTO;;
		5) echo "place -plumber1 -pipe" | $PROTO;;
		6) echo "break -nomad1"  | $PROTO;;
		7) echo "fix -player1"  | $PROTO;;
		8) echo "place -plumber1 -pump" | $PROTO;;
		9) echo "oil -nomad1"  | $PROTO;;
		11) echo "glue -player1"  | $PROTO;;
		12) echo "flow -source" | $PROTO;;
	esac
	echo "Press any key to continue..."
	read go_next
done

