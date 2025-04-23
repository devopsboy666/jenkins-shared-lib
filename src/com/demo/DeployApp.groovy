package com.demo

class DeployApp implements Serializable {
    
    def steps
    def releaseName
    def imageName 
    def imageTag
    def pathHelm

    // Constructor 
    DeployApp(Map args) {
        this.steps = args.steps ?: this
        this.releaseName = args.releaseName ?: ''
        this.imageName = args.imageName.trim() ?: ''
        this.imageTag = args.imageTag.trim() ?: ''
        this.pathHelm = args.pathHelm ?: ''

        if (this.releaseName == '' || this.imageName == '' || this.imageTag == '' || this.pathHelm == '') {
            error("Argument is null")
        } 
        
        this.imageName = "localhost/" + this.imageName  
    }

    def helmDeploy() {
        try {
            steps.sh "helm install ${this.releaseName} ${this.pathHelm} . --set image.name=${this.imageName} --set image.tag=${this.imageTag}"
        } catch (err) {
            steps.error("Fail Helm Deploy ${err.message}")
        }
    }

    def helmUpgrade() {
        try {
            steps.sh "helm upgrade ${this.releaseName} ${this.pathHelm} . --set image.name=${this.imageName} --set image.tag=${this.imageTag}"
        } catch (err) {
            steps.error("Fail Helm Upgrade ${err.message}")
        }
    }

}