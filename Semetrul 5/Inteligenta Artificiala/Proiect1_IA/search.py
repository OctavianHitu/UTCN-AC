



# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""
import random
import util

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """



        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def expand(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (child,
        action, stepCost), where 'child' is a child to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that child.
        """
        util.raiseNotDefined()

    def getActions(self, state):
        """
          state: Search state

        For a given state, this should return a list of possible actions.
        """
        util.raiseNotDefined()

    def getActionCost(self, state, action, next_state):
        """
          state: Search state
          action: action taken at state.
          next_state: next Search state after taking action.

        For a given state, this should return the cost of the (s, a, s') transition.
        """
        util.raiseNotDefined()

    def getNextState(self, state, action):
        """
          state: Search state
          action: action taken at state

        For a given state, this should return the next state after taking action from state.
        """
        util.raiseNotDefined()

    def getCostOfActionSequence(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

    solutie = []
    startPoz = problem.getStartstate()
    while (not (problem.isGoalState(startPoz))):
        succesorii = problem.expand(startPoz)
        nr = len(succesorii)
        randSucc = int(random.random()) % nr
        urm = succesorii[randSucc]
        startPoz = urm[0]
        solutie.append(urm[1])

    print("Solutia este: ", solutie)
    return solutie

def depthFirstSearch(problem):


    startNode = problem.getStartState()
    frontier = util.Stack()
    visitedNodes = []
    frontier.push((startNode, []))

    if problem.isGoalState(startNode):
        return []



    while not frontier.isEmpty():
        currentNode, path = frontier.pop()
        if currentNode not in visitedNodes:
            visitedNodes.append(currentNode)

            if problem.isGoalState(currentNode):
                return path

            for nextNode, action, cost in problem.expand(currentNode):
                newAction = path + [action]
                frontier.push((nextNode, newAction))



   #util.raiseNotDefined()

def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "* YOUR CODE HERE *"

    startNode = problem.getStartState()
    frontier = util.Queue()
    visitedNodes = []
    frontier.push((startNode, []))

    if problem.isGoalState(startNode):
        return []

    while not frontier.isEmpty():
        currentNode, path = frontier.pop()
        if currentNode not in visitedNodes:
            visitedNodes.append(currentNode)

            if problem.isGoalState(currentNode):
                return path

            for nextNode, action, cost in problem.expand(currentNode):
                newAction = path + [action]
                frontier.push((nextNode, newAction))



def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    "* YOUR CODE HERE *"

    frontier = util.PriorityQueue()

    exploredNodes = []

    startState = problem.getStartState()
    startNode = (startState, [], 0)

    frontier.push(startNode, 0)

    while not frontier.isEmpty():

        currentState, actions, currentCost = frontier.pop()

        currentNode = (currentState, currentCost)
        exploredNodes.append((currentState, currentCost))

        if problem.isGoalState(currentState):
            return actions
        else:
            successors = problem.expand(currentState)
            for sState, sAction, sCost in successors:
                newAction = actions + [sAction]
                newCost = problem.getCostOfActionSequence(newAction)
                newNode = (sState, newAction, newCost)

                already_explored = False

                for explored in exploredNodes:
                    exploredState, exploredCost = explored
                    if( sState == exploredState) and (newCost >= exploredCost):
                        already_explored = True

                if not already_explored:
                    frontier.push(newNode, newCost + heuristic(sState, problem))
                    exploredNodes.append((sState, newCost))
    return actions

    #util.raiseNotDefined()


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch