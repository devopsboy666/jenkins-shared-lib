package com.demo

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class DeployAppTest extends BasePipelineTest {

    def script
    def mockShCalls = []

    @Before
    void setUp() {
        super.setUp()
        // mock steps.sh
        binding.setVariable('sh', { cmd -> mockShCalls << cmd })
        // mock steps.error
        binding.setVariable('error', { msg -> throw new RuntimeException("ERROR: ${msg}") })
    }

    @Test
    void testDeployAppSuccess() {
        def build = new DeployApp([
            steps: this.binding,
            releaseName: "demo",
            imageName: "nameImage",
            imageTag: "tag0",
            pathHelm: "helmpath"
        ])
        build.helmDeploy() 

        assertTrue(mockShCalls[0].contains("helm upgrade --install demo helmpath . --set image.name=localhost/nameImage --set image.tag=tag0"))

    }


    @Test(expected = RuntimeException.class)
    void testMissingDeploy() {
        new DeployApp([
            steps: this.binding
        ])
    }

}
