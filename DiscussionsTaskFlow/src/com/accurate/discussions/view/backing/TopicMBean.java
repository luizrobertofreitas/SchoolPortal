package com.accurate.discussions.view.backing;

import com.accurate.discussions.utils.Constants;
import com.accurate.discussions.utils.Utils;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import oracle.webcenter.collab.forum.CategoryService;
import oracle.webcenter.collab.forum.ForumException;
import oracle.webcenter.collab.forum.ForumNotFoundException;
import oracle.webcenter.collab.forum.ForumService;
import oracle.webcenter.collab.forum.ForumSession;
import oracle.webcenter.collab.forum.ResultFilter;
import oracle.webcenter.collab.forum.Topic;
import oracle.webcenter.collab.share.LoginFailedException;
import oracle.webcenter.collab.share.SessionException;
import oracle.webcenter.collab.share.UnauthorizedException;

/**
 * @author Accurate Software
 */
public class TopicMBean implements Serializable {

    @SuppressWarnings("compatibility:-5153086200073981080")
    private static final long serialVersionUID = -525858198755358868L;
    
    private List<Topic> topics;
    
    private transient ForumSession forumSession;
    private transient ForumService forumService;
    
    @PostConstruct
    public void init() {
        try {
            forumSession = ForumSession.getSession();
            
            if (forumSession != null) {
                forumService = (ForumService) forumSession.getService(ForumService.class);
            }
            
            loadTopics();
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

    /**
     * Load all topics from selected forum
     * 
     * @throws ForumNotFoundException
     * @throws UnauthorizedException
     * @throws SessionException
     */
    private void loadTopics() throws ForumNotFoundException, UnauthorizedException, SessionException {
        
        String forumId = Utils.getParamPageFlow(Constants.PAGEFLOW_FORUM_ID_PARAM, String.class);
        
        System.out.println(" >>>>>>>>>>>>>>>>> Topic, forumId " + forumId);
        
        if (forumService != null) {
            topics = forumService.getTopics(forumId, ResultFilter.createDefaultResultFilter());
        }
    }

    public List<Topic> getTopics() {
        return topics;
    }
}
