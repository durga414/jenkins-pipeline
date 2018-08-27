#!/bin/groovy

package com.devops.batch.build

def createBuild(PATH_POM , MAVEN_VERSION ,GOALS ){
     try {
    wrap([$class: 'AnsiColorBuildWrapper']) {
    echo "${WORKSPACE}"
    echo "${MAVEN_VERSION}"
	sh "'${MAVEN_VERSION}'/bin/mvn -f '${PATH_POM}' ${GOALS}"
    print "\u001B[32m[INFO]: Successfully Executing the Build..."
	}
    }
    catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to Build"
        throw error
     }
   }
}