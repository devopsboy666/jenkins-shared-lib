package com.demo

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class BuildImageTest extends BasePipelineTest {

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
    void testBuildImageSuccess() {
        def build = new BuildImage([
            steps: this.binding,
            imageName: "nameImage",
            imageTag: "tag0",
            dockerfilePath: "app"
        ])
        build.dockerBuild()

        assertTrue(mockShCalls[0].contains("podman build -t nameImage:tag0 app/"))
    }

    @Test(expected = RuntimeException.class)
    void testMissingBuildImage() {
        new CloneRepo([
            steps: this.binding
        ])
    }

}
