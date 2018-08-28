#!/bin/groovy

package com.devops.batch.properties

def setProperties()
{
    try{        
    properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5')), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false]])
    }
    catch (error) {
       wrap([$class: 'AnsiColorBuildWrapper']) {
          print "\u001B[41m[ERROR] Failed to set the jenkins properties, please check the logs..."
          throw error
       }
   }
}