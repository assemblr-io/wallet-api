if [[ "$1" != "dev" && "$1" != "prod" ]]
  then
    echo "ERROR: please specify 'dev' or 'prod' as argument eg. run.sh dev || run.sh prod"
    exit
fi

#clean the project
./gradlew clean

#build the project
./gradlew distTar

#make sure the docker compose app isn't already running and if it is - tear it down
docker compose down

#using commandline to set env - for convenience of review
if [[ "$1" == "dev" ]]; then docker compose up lnd-wallets-api-"$1" newman-dev
elif [[ "$1" == "prod" ]]; then docker compose up lnd-wallets-db lnd-wallets-api-prod newman-prod
fi