package com.demo

class BuildImage implements Serializable {

    def steps 
    def imageName
    def imageTag
    def dockerfilePath

    BuildImage(Map args) {
        this.steps = args.steps
        this.imageName = args.imageName ?: ''
        this.imageTag = args.imageTag ?: ''
        this.dockerfilePath = args.dockerfilePath ?: ''

        if (this.imageName == '' || this.imageTag == '' || this.dockerfilePath == '') {
            error("args is empty --> imageName, imageTag, dockerfilePath")
        }
    }

    def dockerBuild() {
        try {
            steps.sh "sudo podman build -t ${this.imageName}:${this.imageTag} ${this.dockerfilePath}/"
        } catch (err) {
            steps.error("Error From DockerBuild Method ${err.message}")
        }
    }
}