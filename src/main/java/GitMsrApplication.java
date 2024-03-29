package alberta.sn.hm.msr;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@SpringBootApplication
public class GitMsrApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GitMsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	
        try (Repository repository = openRepository()) {
            try (Git git = new Git(repository)) {
                Iterable<RevCommit> commits = git.log().all().call();

                Iterator<RevCommit> iterator = commits.iterator();
                while (iterator.hasNext()) {
                    RevCommit commit = iterator.next();
                    System.out.println("LogCommit: " + commit);
                }
            }
        }
    }

    public static Repository openRepository() throws IOException {
        return Git.open(new File("data")).getRepository();
    }
}
