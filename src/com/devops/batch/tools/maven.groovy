#!/bin/groovy
package com.devops.batch.tools

def setMavenHome(MVN_VERSION)
{
   try {
     wrap([$class: 'AnsiColorBuildWrapper']) {
       env.MAVEN_VERSION="${tool "${MVN_VERSION}"}"
       echo "'${MAVEN_VERSION}'"
      //echo "'${MAVEN_HOME}'"
       sh "${MAVEN_VERSION}/bin/mvn --version"
	   }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to failed to set Maven ."
        throw error
     }
   }
}