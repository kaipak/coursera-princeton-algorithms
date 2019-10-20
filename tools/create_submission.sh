#!/bin/bash

files=""

while getopts ":d:f:" arg; do
    case $arg in
        d) DIR=$OPTARG;;
	f) FILEZIP=$OPTARG;;
    esac
done

find $DIR/*.java -type f -exec zip $FILEZIP.zip {} \;

