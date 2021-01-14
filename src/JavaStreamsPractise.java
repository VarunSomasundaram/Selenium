import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class JavaStreamsPractise {

	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("First Print");
	}
	@Test(groups= {"Regression"})
	public static void JavaStreamPractice() {
		// TODO Auto-generated method stub
		
		List<String> names = new ArrayList<String>();
		names.add("David");
		names.add("Manny");
		names.add("Yuvi");
		names.add("Rakesh");
		names.add("Ram");
		
		names.stream().filter(s-> s.startsWith("R")).forEach(s->System.out.println(s));
		System.out.println("********************");
		names.stream().map(s-> s.toLowerCase()).sorted().forEach(s->System.out.println(s));
		System.out.println("********************");
		System.out.println(names.stream().filter(s->s.contains("i")).count());
		System.out.println("********************");
		List<String> sortedNames = names.stream().filter(s-> s.length()<5).map(s->s.toUpperCase()).sorted().limit(2).collect(Collectors.toList());
		System.out.println(sortedNames.get(1));
		
		
		System.out.println("*********FINAL********");		
		Stream<String> finalStream = Stream.concat(names.stream(),Stream.of("Ravi","Kumar","Babu", "Pandy"));
		finalStream.sorted().forEach(s->System.out.println(s));
		
	}

}
