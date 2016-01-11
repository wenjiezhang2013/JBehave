package it.jz.helloworld.jbehave.story;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import it.jz.helloworld.jbehave.model.TeacherSteps;

@RunWith(BlockJUnit4ClassRunner.class)
public class NewStudentStories extends JUnitStories {
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new TeacherSteps());
    }

    @Override
    protected List<String> storyPaths() {
        System.err.println(CodeLocations.codeLocationFromClass(getClass()));
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(getClass()), "**/*.story", "");
    }

}