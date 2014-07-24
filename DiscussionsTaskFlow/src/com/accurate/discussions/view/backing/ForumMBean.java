package com.accurate.discussions.view.backing;

import com.accurate.discussions.utils.Constants;
import com.accurate.discussions.utils.Utils;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;

import oracle.webcenter.collab.forum.CategoryNotFoundException;
import oracle.webcenter.collab.forum.CategoryService;
import oracle.webcenter.collab.forum.Forum;
import oracle.webcenter.collab.forum.ForumException;
import oracle.webcenter.collab.forum.ForumNotFoundException;
import oracle.webcenter.collab.forum.ForumService;
import oracle.webcenter.collab.forum.ForumSession;
import oracle.webcenter.collab.forum.Message;
import oracle.webcenter.collab.forum.MessageNotFoundException;
import oracle.webcenter.collab.forum.MessageService;
import oracle.webcenter.collab.forum.ResultFilter;
import oracle.webcenter.collab.forum.TopicNotFoundException;
import oracle.webcenter.collab.share.AttachmentException;
import oracle.webcenter.collab.share.LoginFailedException;
import oracle.webcenter.collab.share.SessionException;
import oracle.webcenter.collab.share.UnauthorizedException;
import oracle.webcenter.collab.share.UserNotFoundException;

public class ForumMBean implements Serializable {
    
    @SuppressWarnings("compatibility:-1074075206508352302")
    private static final long serialVersionUID = -310562070727790843L;
    
    private static final Boolean LOAD_FORUMS_RECURSIVELLY = true;
    
    private String rootCategoryId;
    private String selectedForumId;
    private List<Forum> forums;
    
    private transient ForumSession forumSession;
    private transient CategoryService categoryService;
    private transient ForumService forumService;
    private transient MessageService messageService;
    
    @PostConstruct
    public void init() {
        try {
            forumSession = ForumSession.getSession();
            
            if (forumSession != null) {
                forumService = (ForumService) forumSession.getService(ForumService.class);
                messageService = (MessageService) forumSession.getService(MessageService.class);
                categoryService = (CategoryService) forumSession.getService(CategoryService.class);
                rootCategoryId = categoryService.getRootCategoryId();
            }
            
            loadForums();
        } 
        catch (LoginFailedException e) {
            e.printStackTrace();
        }
        catch (ForumException e) {
            e.printStackTrace();
        } 
        catch (UnauthorizedException e) {
            e.printStackTrace();
        }
        catch (SessionException e) {
            e.printStackTrace();
        }
    }
    
    private void loadForums() throws CategoryNotFoundException, UnauthorizedException, SessionException {
        if (categoryService != null) {
            forums = categoryService.getForums(rootCategoryId, ResultFilter.createDefaultResultFilter(), LOAD_FORUMS_RECURSIVELLY);
        }
    }
    
    public void selectForumActionListener(ActionEvent actionEvent) {
        
//        if (messageService != null) {
//
//            try {
//                Message message = new Message();
//                message.setForumId("1");
//                message.setTopicId("1");
//                message.setParentId("1");
//                message.setSubject("Subject");
//                message.setBody("Body teste");
//                
//                messageService.createMessage(message, null);
//            }
//            catch (ForumNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (TopicNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (MessageNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (AttachmentException e) {
//                e.printStackTrace();
//            }
//            catch (UserNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (UnauthorizedException e) {
//                e.printStackTrace();
//            }
//            catch (SessionException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>> ForumMBean Selected ForumId: " + selectedForumId);
        Utils.addParamPageFlow(Constants.PAGEFLOW_FORUM_ID_PARAM, selectedForumId);
        
    }
    
    public List<Forum> getForums() {
        return forums;
    }
    
    public Integer getForumsSize() {
        Integer size = 0;
        
        if (forums != null) {
            size = forums.size();
        }
        
        return size;
    }

    public void setSelectedForumId(String selectedForumId) {
        this.selectedForumId = selectedForumId;
    }

    public String getSelectedForumId() {
        return selectedForumId;
    }
}
