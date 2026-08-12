pipeline {

    agent any

//     stages {
//
//         stage('Checkout') {
//
//             steps {
//                 git 'https://github.com/rishabhgupta3119/iris.git'
//             }
//         }

        stage('Build & Test') {

            steps {
                bat 'mvn clean test'
            }
        }
    }
}