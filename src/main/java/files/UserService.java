package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UserService {

    private List<UserAction> actions = new LinkedList<>();

    public void addFile(File file) throws IOException {
        LineIterator fileContents = FileUtils.lineIterator(file, "UTF-8");
        while (fileContents.hasNext()) {
            String[] line = fileContents.nextLine().split(";");
            if (!(line[0].equals("action"))) {
                if(line[0].equals("CREATE")) {
                    addUser(new UserAction( line[1], line[2], line[3], line[4], line[5]));
                }else if(line[0].equals("UPDATE")){
                    updateUser(line);
                }else if(line[0].equals("REMOVE")){
                    removeUser(line[1]);
                }
            }
        }
    }
    public void addUser(UserAction userAction){
        actions.add(userAction);
    }
    public void updateUser(String[] line){
        for(UserAction userAction: actions){
            if(userAction.getLogin().equals(line[1])){
                if(!line[2].isEmpty()){
                    userAction.setPassword(line[2]);
                }else if(!line[3].isEmpty()){
                    userAction.setFirstname(line[3]);
                }else if(!line[4].isEmpty()){
                    userAction.setLastname(line[4]);
                }else if(!line[5].isEmpty()){
                    userAction.setRule(line[5]);
                }
            }
        }
    }
    public void removeUser(String login){
        for(int i=0;i<actions.size();i++){
            if(actions.get(i).getLogin().equals(login)){
                actions.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "UserService{" +
                "actions=" + actions +
                '}';
    }
}
