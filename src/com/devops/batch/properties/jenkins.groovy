#!/bin/groovy

package com.devops.batch.propeties

def setProperties()
{
    try{
        
    wrap([$class: 'AnsiColorBuildWrapper']) {
          properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5')), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false]])
          print "\u001B[41m[ERROR] Successfully Setup the jenkins properties, please check the logs..."
       }
    }
    catch (error) {
       wrap([$class: 'AnsiColorBuildWrapper']) {
          print "\u001B[41m[ERROR] Failed to set the jenkins properties, please check the logs..."
          throw error
       }
   }
}