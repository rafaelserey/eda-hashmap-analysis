
#flag para interromper no primeiro erro
set -e

# build do mvn
mvn clean install
mvn clean package

# rodando benchmark e transferindo para um temp.csv
java -jar target/benchmarks.jar -rf csv -rff temp.csv

# aplicando o temp.csv ao results
if [ -f results.csv ]; then
  tail -n +2 temp.csv >> results.csv
else
  cp temp.csv results.csv
fi

# remove o temp.csv
rm temp.csv

