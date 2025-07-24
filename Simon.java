import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;
public class Simon {
	public static void main(String[] args) throws InterruptedException {
		Scanner kb = new Scanner(System.in);
		Random ran = new Random();
		
		boolean isCorrect = true;
		int score = 0;
		
		System.out.println("Let's play Simon!\n");
		
		while(isCorrect) {
			ArrayList<String> gameSequence = new ArrayList<>();
			
			String[] colors = {"green","red","yellow","blue"};
			
			for(int i=0; i<score+1;i++) {
				gameSequence.add(colors[ran.nextInt(colors.length)]);
			}
			
			for(int i=0;i<gameSequence.size();i++) {
				System.out.print(gameSequence.get(i));
				Thread.sleep(1500);
				for(int j=0; j<1000;j++) {
					System.out.print("\n");
				}
			}
			ArrayList<String> userSequence = new ArrayList<>();
			
			String userInput = kb.nextLine();
			
			String[] userWords = userInput.toLowerCase().split("\\s+");			

			for(String x:userWords) {
				userSequence.add(x);
			}
			
			isCorrect = gameSequence.equals(userSequence);
			if(isCorrect) {
				score++;
				System.out.println("Correct! Your current score is "+score+"\n");
			}
			
			Thread.sleep(2000);
			for(int j=0; j<1000;j++) {
				System.out.print("\n");
			}
		}
		System.out.println("Game over! Your score is "+score);
	}
}
