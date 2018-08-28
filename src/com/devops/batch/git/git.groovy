#!/bin/groovy

package com.devops.batch.git

def gitCheckout(BRANCH){
    try{
    checkout([$class: 'GitSCM', branches: [[name: '*/${BRANCH}']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'be9be694-ee29-4fb5-9bd5-ea491d6a8e2d', url: 'https://github.com/ithelpstream/shopizer.git']]])
    print "\u001B[41m[ERROR] Successfully clone the Repository..Validate the logs..."
    }
    catch (error) {
    wrap([$class: 'AnsiColorBuildWrapper']) {
    print "\u001B[41m[ERROR] Failed to clone the Repository..please check the logs..."
    throw error
    }
    }
}