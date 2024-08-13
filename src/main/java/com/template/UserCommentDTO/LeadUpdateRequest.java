package com.template.UserCommentDTO;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import lombok.Data;

import java.util.List;

@Data
public class LeadUpdateRequest {
    private LeadFollowUp leadFollowUp;
    private List<Comment> commentObjects; // For Comment objects
    private List<String> comments;
}
