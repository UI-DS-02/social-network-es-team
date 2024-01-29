package usersinfo;

import implementations.Heap.MaxHeapPriorityQueue;

import java.util.LinkedList;

class User {
    private GeneralInfo generalInfo;
    private UserSpecializedInfo specializedInfo;
    private UserSkills skills;
    private Connections connections;
    private UserPriorities priority;        //for calculating score of people
    private MaxHeapPriorityQueue<Integer, User> suggestions;
    //key:ID  value:score


    public User(GeneralInfo generalInfo, UserSpecializedInfo specializedInfo, UserSkills skills, Connections connections, UserPriorities priority, MaxHeapPriorityQueue<Integer, User> suggestions, LinkedList<User> users) {
        this.generalInfo = generalInfo;
        this.specializedInfo = specializedInfo;
        this.skills = skills;
        this.connections = connections;
        this.priority = priority;
        this.suggestions = suggestions;

        for (User user : users) {
            findSuggestions(user);
            if (this.suggestions.size() == 20) break;
        }
    }

    int generalCalculate(User user) {
        int counter = 0;
        //calculate generalInfo;
        if (this.generalInfo.getLastname().equals(user.generalInfo.getLastname())) counter++;
        if (this.generalInfo.getBirth().getYear() == user.generalInfo.getBirth().getYear()) counter++;

        return (this.priority.getGeneralPriority() * counter);
    }

    int specializedCalculate(User user) {
        int counter = 0;
        //calculate specializedInfo;

        if (this.specializedInfo.getFieldOfStudy().equals(user.specializedInfo.getFieldOfStudy())) counter++;
        if (this.specializedInfo.getUniversity().equals(user.specializedInfo.getUniversity())) counter++;
        if (this.specializedInfo.getWorkPlace().equals(user.specializedInfo.getWorkPlace())) counter++;

        return (this.priority.getSpecializedPriority() * counter);
    }

    int skillsCalculate(User user) {
        int counter = 0;
        //calculate skills;
        for (String skill : this.skills.getSkills()) {
            if (user.skills.getSkills().contains(skill)) counter++;
        }

        return (this.priority.getSkillPriority() * counter);
    }

    int connectionsCalculate(User user) {
        int counter = 0;
        //calculate connections;
        for (int connection : this.connections.getUsers()) {
            if (user.connections.getUsers().contains(connection)) {
                counter++;
            }
        }
        return (this.priority.getConnectionPriority() * counter);
    }

    void findSuggestions(User user) {
        int score = generalCalculate(user) + specializedCalculate(user) + skillsCalculate(user) + connectionsCalculate(user);   //sum all scores
        this.suggestions.insert(score, user);
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public UserSpecializedInfo getSpecializedInfo() {
        return specializedInfo;
    }

    public void setSpecializedInfo(UserSpecializedInfo specializedInfo) {
        this.specializedInfo = specializedInfo;
    }

    public UserSkills getSkills() {
        return skills;
    }

    public void setSkills(UserSkills skills) {
        this.skills = skills;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public UserPriorities getPriority() {
        return priority;
    }

    public void setPriority(UserPriorities priority) {
        this.priority = priority;
    }

    public MaxHeapPriorityQueue<Integer, User> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(MaxHeapPriorityQueue<Integer, User> suggestions) {
        this.suggestions = suggestions;
    }
}