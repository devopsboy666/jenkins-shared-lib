package com.demo

class CloneRepo implements Serializable {
    
    def steps

    CloneRepo(steps) {
        this.steps = steps
    }

    def sayHello(String message) {
        steps.echo "Hello ${message}"
    }

}