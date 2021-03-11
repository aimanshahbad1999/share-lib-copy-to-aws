// #!/usr/bin/env groovy
def call(){

    node{
        stage('Pull From Github'){
            git branch: 'main', credentialsId: 'cfdbf68d-a49e-4901-bbc2-2273836adfa1', url: 'https://github.com/aimanshahbad1999/python-git.git'
        }
        stage('Read File'){
            print("Reading Python File")
            def data = readFile(file: 'python.py')
            // def newFile = new File("/home/remote_user/workspace/copyFileToAws/PythonBackup.txt")
            def content=writeFile(file: 'PythonBackup.txt', text: data)
        }
        stage('Backup to AWS s3'){
            sh '''
                export AWS_ACCESS_KEY_ID=AKIA52S3JMKJC35LHP5H
                export AWS_SECRET_ACCESS_KEY=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
                aws s3 cp PythonBackup.txt s3://pythonfilebackup/python.txt
                '''
        }
    }
}