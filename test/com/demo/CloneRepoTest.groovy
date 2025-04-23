package com.demo

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class CloneRepoTest extends BasePipelineTest {

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
    void testCloneRepoSuccess() {
        def repo = new CloneRepo([
            steps: this.binding,
            repoUrl: "https://github.com/example/repo.git",
            branch: "develop"
        ])
        repo.gitCloneRepo()

        assertTrue(mockShCalls[0].contains("git clone -b develop https://github.com/example/repo.git"))
    }

    @Test(expected = RuntimeException.class)
    void testMissingRepoUrl() {
        new CloneRepo([
            steps: this.binding
        ])
    }

    @Test
    void testDefaultBranchIsMain() {
        def repo = new CloneRepo([
            steps: this.binding,
            repoUrl: "https://github.com/example/repo.git"
        ])
        assertEquals("main", repo.branch)
    }

}
