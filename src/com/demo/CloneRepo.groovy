package com.demo

class CloneRepo implements Serializable {
    
    def steps
    def branch 
    def repoUrl

    // Constructor 
    CloneRepo(Map args) {
        if (!args.steps) throw new IllegalArgumentException("Missing required argument: steps")
        this.steps = args.steps
        this.branch = args.branch :? 'main'
        this.repoUrl = args.repoUrl :? ''

        if (this.repoUrl == '') {
            error("Repo URL is empty")
        }
    }

    gitCloneRepo() {
        try {
            steps.sh "git clone -b ${this.branch} ${this.repoUrl}"
        } catch (err) {
            error("Git Clone Fail..\n${err.message}")
        }
    }

}