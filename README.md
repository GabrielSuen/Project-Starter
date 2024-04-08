# CPSC 210 Personal Project

## Simple Game Project 

***What will the application do?***

The application centers around a simple game. The game revolves 
around two main elements: a platform, and a falling object. The
goal of the game is to land the falling object onto the platform.
Once accomplished the object will reset at the top of the screen 
and the platform will be located at a different place. The idea is
that as you continuously land the object on the platform, the speed
at which the object falls increases and you gain a point. I want 
to be able to record high scores into "profiles" which can save the 
state (level) a player is at. Furthermore I want to be able to 
compare player scores and see statistics on how likely it would be 
for one player to overtake the other in points.

***Who can use it?***

The game should have appropriate instructions and an easy to understand
interface that anyone can use. Additionally the game should be able
to be played with only a few keystrokes, accessible to any disabled 
or uncoordinated players.

***Why is this project of interest to you?***

This projects interests me as I've always enjoyed simple high-score
type games and I've relied on them to pass the time on many occasions
I thought it would be interesting to design my own and see what the
process is for making these types of games.

***User Stories***: 

As a user I want to...

- Be able to add new save profiles with a name and the recorded 
score (point count) 
- Be able to compare how likely one player is to overtake another score
- Be able to choose a profile to "challenge", setting up their score
in the top left of the game screen. Once the player passes that
score, their score lights up
- Be able to view a list of profiles with their high score and name
- Be able to save the current leaderboard consisting of player names and scores
- Be able to load the saved leaderboard from file

***<u> Phase 4: Task 2 </u>***

Sun Apr 07 16:13:35 PDT 2024 \
Game Started.\
Sun Apr 07 16:13:48 PDT 2024\
Profile Added. \
Sun Apr 07 16:13:57 PDT 2024 \
Profile Added. \
Sun Apr 07 16:13:58 PDT 2024 \
Profiles Shown. \
Sun Apr 07 16:14:04 PDT 2024 \
Challenger Set. \
Sun Apr 07 16:14:22 PDT 2024 \
Challenger Set. \
Sun Apr 07 16:14:27 PDT 2024 \
ProfilesCompared. 

***<u> Phase 4: Task 3 </u>***

Upon creating the UML Diagram I realised that there were a few places
my code could be improved had I kept my mind open to abstraction. 
I first noticed this in my handling of the profiles or player ID's.
The MenuPanel aspect of my UI ended up being tightly related to the
fields in the ProfileManager class. Additionally, it used many of the
methods implemented in the ProfileManager class. In hindsight, I think
it would make sense to have the MenuPanel class extend the ProfileManager
class so that it would have direct access to those fields and methods.
Another potential problem I noticed with my design is that the association 
between GamePanel and MenuPanel is actually unnecessary given the presence
of my AppFull class which combines the two into one JFrame. The only
use of the association is for a single method which could have easily
been implemented in AppFull or even MenuPanel itself. 

Finally, the last improvement I would make would be implementing the
singleton pattern into the ProfileManager class. I noticed that I 
often had problems while designing other classes which depended on 
ProfileManager, that it would often not reference the correct
instantiation of the current ProfileManager I needed. In reality, I only 
needed one instance of ProfileManager at any given time since all 
classes had to reference the same ProfileManager. Implementing the 
singleton pattern would be pretty seamless and applicable in this situation
and it would also alleviate some headache when designing other classes.