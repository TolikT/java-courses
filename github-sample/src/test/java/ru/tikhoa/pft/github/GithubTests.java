package ru.tikhoa.pft.github;


import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("7a29ae1dbe03dadbec17d7dd0921239fe83eeb5f");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("TolikT", "java-courses")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
