package com.demo

class CloneRepo implements Serializable {
    
    def steps
    def branch 
    def repoUrl

    // Constructor 
    CloneRepo(Map args) {
        this.steps = args.steps ?: this
        this.branch = args.branch ?: 'main'
        this.repoUrl = args.repoUrl ?: ''

        if (this.repoUrl == '') {
            error("Repo URL is empty")
        }
    }

    def gitCloneRepo() {
        try {
            steps.sh "git clone -b ${this.branch} ${this.repoUrl}"
        } catch (err) {
            steps.error("Git Clone Fail: ${err.message}")
        }
    }

}