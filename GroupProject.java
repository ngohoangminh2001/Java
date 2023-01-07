/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package groupproject;
import java.util.*;

public class GroupProject {
    protected final int COLUMN = 3, NUM_PLAYERS = 4, ROW = 5;
    protected int matrix[][] = {{0, 1, 2}, {1, 2, 0}, {2, 1, 0}, {0, 2, 1}, {1, 2, 0}};
    
    public int getTotalColumn()
    {
        return COLUMN;
    }
    
    public int getNumberOfPlayers()
    {
        return NUM_PLAYERS;
    }
    
    public int getTotalRow()
    {
        return ROW;
    }
    
    public void setMatrixCell(int x, int y, int k)
    {
        this.matrix[x][y] = k;
    }
    
    public int getMatrixCell(int x, int y)
    {
        return this.matrix[x][y];
    }
    
    public void showMatrix()
    {
        for (int x = 0; x < getTotalRow(); x++)
        {
            for (int y = 0; y < getTotalColumn(); y++)
            {
                if (getMatrixCell(x, y) == 0) System.out.printf("%15s", " Green ");
                else if (getMatrixCell(x, y) == 1) System.out.printf("%15s", " Yellow ");
                else if (getMatrixCell(x, y) == 2) System.out.printf("%15s", " Red ");
            }
            
            System.out.println();
        }
        
        System.out.println();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        GroupProject object = new GroupProject();
        int choice = 0, r, c, temporary, colorIndex, color;
        char cont = ' ', again = ' ';
        String name;
        ArrayList<Player> players = new ArrayList<Player>();
        
        do {
            System.out.println("\nGame name: the last passenger\n");
            System.out.println("MENU------------------------------------------------------");
            System.out.println("Choose the number corresponding to what you want to do:");
            System.out.println("1. Show the initial matrix");
            System.out.println("2. Change the initial matrix");
            System.out.println("3. Play");
            System.out.println("4. Show results");
            System.out.println("5. Show the player who has the highest score");
            System.out.println("6. Show the player who has the lowest score");
            System.out.println("7. Quit the program");
            
            System.out.print("\nEnter your option: ");
            do {
                choice = input.nextInt();
                if (choice<1 || choice>7) System.out.print("Invalid choice. Please input again: ");
            } while (choice<1 || choice>7);
            
            System.out.println();
            
            if (choice == 1)
            {
                System.out.println("Initial matrix:");
                object.showMatrix();
            }
            
            else if (choice == 2)
            {
                do {
                    System.out.println("* Enter the coordinates of the location where you want to change the color:");
                    
                    System.out.print("+ Row: ");
                    r = input.nextInt();
                    while (r < 1 || r > object.getTotalRow())
                    {
                        System.out.print("+ + Invalid row. Please enter again: ");
                        r = input.nextInt();
                    }
                    
                    System.out.print("+ Column: ");
                    c = input.nextInt();
                    while (c < 1 || c > object.getTotalColumn())
                    {
                        System.out.print("+ + Invalid column. Please enter again: ");
                        c = input.nextInt();
                    }
                    
                    temporary = object.getMatrixCell(r - 1, c - 1);
                    
                    System.out.print("* Input the number corresponding to the new color (input 0 for green, input 1 for yellow, input 2 for red): ");
                    colorIndex = input.nextInt();
                    while (colorIndex < 0 || colorIndex > 2)
                    {
                        System.out.print("+ Invalid number. Please input again: ");
                        colorIndex = input.nextInt();
                    }

                    if (colorIndex != temporary)
                    {
                        object.setMatrixCell(r - 1, c - 1, colorIndex);
                        
                        for (int i = 0; i < object.getTotalColumn(); i++)
                        {
                            if (i != c - 1 && object.getMatrixCell(r - 1, i) == temporary)
                            {
                                object.setMatrixCell(r - 1, i, temporary);
                                break;
                            }
                        }
                        
                        System.out.println("\nChange the color of the cell [row][column] = [" + r + "][" + c + "] successfully.");
                        System.out.println("\nEdited matrix:");
                    }
                    else
                    {
                        System.out.print("\nThe current color of the cell [row][column] = [" + r + "][" + c + "] is ");
                        if (colorIndex == 0) System.out.print("green");
                        else if (colorIndex == 1) System.out.print("yellow");
                        else if (colorIndex == 2) System.out.print("red");
                        System.out.println(". Therefore the system cannot change the color at this cell.");
                        System.out.println("\nMatrix:");
                    }
                    
                    object.showMatrix();
                    
                    System.out.print("Do you want to change the color at another location? (y/n) ");
                    do {
                        again = input.next().charAt(0);
                        if (again != 'Y' && again != 'y' && again != 'N' && again != 'n') System.out.print("Invalid answer. Please enter again: ");
                    } while (again != 'Y' && again != 'y' && again != 'N' && again != 'n');
                    
                    System.out.println();
                } while (again == 'y' || again == 'Y');
            }
            
            else if (choice == 3)
            {
                for (int i = 0; i < object.getNumberOfPlayers(); i++)
                {
                    System.out.println("Player #" + (i + 1) + ":");
                    System.out.print("+ Name: ");
                    
                    input.nextLine();
                    name = input.nextLine();
                    
                    char[] charArray = name.toCharArray();
                    boolean space = true;
                    for (int j = 0; j < charArray.length; j++)
                    {
                        if (Character.isLetter(charArray[j]))
                        {
                            if (space)
                            {
                                charArray[j] = Character.toUpperCase(charArray[j]);
                                space = false;
                            }
                        }
                        else space = true;
                    }
                    name = String.valueOf(charArray);

                    System.out.print("+ Choose your color (input 0 for green, input 1 for yellow, input 2 for red): ");
                    color = input.nextInt();
                    while (color < 0 || color > 2)
                    {
                        System.out.print("+ + Invalid color index. Please enter again (input 0 for green, input 1 for yellow, input 2 for red): ");
                        color = input.nextInt();
                    }
                    
                    players.add(new Player(name, color));
                    
                    for (int j = 0; j < object.getTotalRow(); j++)
                    {
                        System.out.println("* Row #" + (j + 1) + ":");
                        System.out.print("* * Choose your cell (an integer from 0 to 2): ");
                        int k = input.nextInt();
                        while (k < 0 || k > 2)
                        {
                            System.out.print("* * * Invalid cell number. Please enter again (an integer from 0 to 2): ");
                            k = input.nextInt();
                        }
                        
                        System.out.print("* * * Your choice is ");
                        
                        if (players.get(i).getColor() == object.getMatrixCell(j, k)) 
                        {
                            System.out.println("true. Continue playing...");
                            players.get(i).addScore();
                        }
                        else 
                        {
                            System.out.println("false. We are sorry to say that you will end the game here. You get " + players.get(i).getScore() + " point(s).");
                            break;
                        }
                    }
                    
                    System.out.println();
                } 
            }
            
            else if (choice == 4)
            { 
                System.out.printf("%-20s%-28s%-18s%-4s\n", "Luot choi", "Nguoi choi", "Mau lua chon", "Diem");
                
              for(int i=0;i<4;i++)
              {  
                 String cl=" ";
                 if (players.get(i).getColor() == 0) cl="Xanh";
                 else if (players.get(i).getColor()==1) cl="Vang";
                 else if (players.get(i).getColor()==2) cl="Do";
              System.out.printf("%-20d%-28s%-18s%-4d\n", i+1, players.get(i).getName(), cl, players.get(i).getScore());
              }
              
              System.out.println();
            }
            
            else if (choice == 5)
            {  
                int y = 0, max = players.get(y).getScore();
                
                for(int i = 0; i < 4; i++)
                {
                    if (players.get(i).getScore() > max)
                    {
                        max = players.get(i).getScore();
                        y = i;
                    }
                }
                
                System.out.printf("%-20d%-18s%-4d\n", "No.", "Name", "Score");
                System.out.printf("%-20d%-18s%-4d\n", y+1, players.get(y).getName(), players.get(y).getScore());
                System.out.println();
            }
            
            else if (choice == 6)
            {
                int m = 0, min = players.get(m).getScore();
                
                for(int i = 0; i < 4; i++)
                {
                    if (players.get(i).getScore() < min)
                    {
                        min = players.get(i).getScore();
                        m = i;
                    }
                }
                
                System.out.printf("%-20d%-18s%-4d\n", "No.", "Name", "Score");
                System.out.printf("%-20d%-18s%-4d\n", m+1, players.get(m).getName(), players.get(m).getScore());
                System.out.println();
            }
            
            if (choice >= 1 && choice <= 6)
            {
                System.out.print("Do you want to continue using the program? (y/n) ");
                do {
                    cont = input.next().charAt(0);
                    if (cont != 'Y' && cont != 'y' && cont != 'N' && cont != 'n') System.out.print("Invalid answer. Please enter again: ");
                } while (cont != 'Y' && cont != 'y' && cont != 'N' && cont != 'n');
            }
            
        } while ((cont == 'y' || cont == 'Y') && choice != 7);
        
        if (choice == 7 || cont == 'N' || cont == 'n') System.out.println("\nThe program has ended!\n");
        
        System.exit(0);
    }  
}

class Player {
    protected String name;
    protected int color, score = 0;
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setColor(int color)
    {
        this.color = color;
    }
    
    public int getColor()
    {
        return color;
    }
    
    public void addScore()
    {
        score += 1;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public Player(String name, int color)
    {
        setName(name);
        setColor(color);
    }
}