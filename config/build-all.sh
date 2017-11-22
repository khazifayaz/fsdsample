#!/usr/bin/env bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e

note "Building config...";          gradle :core-config-server:buildDocker;
note "Building sample-server...";   gradle :sample-server:buildDockerMysql;