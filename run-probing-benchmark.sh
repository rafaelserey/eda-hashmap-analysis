#!/usr/bin/bash

# flag para interromper no primeiro erro
set -e

# build do mvn
mvn clean install
mvn clean package

# rodando apenas benchmarks com "Probing" no nome e transferindo para um temp.csv
java -jar target/benchmarks.jar ".*Probing.*" -rf csv -rff temp.csv 

# aplicando o temp.csv ao results
if [ -f probing_results.csv ]; then
  tail -n +2 temp.csv >> probing_results.csv
else
  cp temp.csv probing_results.csv
fi

# remove o temp.csv
rm temp.csv

