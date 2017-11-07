#!/bin/bash

    numCalls=$1

    for (( i=1; i<=$numCalls; i++ ))
    do
        echo "Doing run $i"
        java echoserver.EchoClient < Crysis1.jpg > /dev/null &
    done
    echo "Now waiting"
    date
    wait
    echo "Done"
    date
