package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
public class ControllerTestUtility {

    // add organization
    public static OrganizationDto.Response addOrganization(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String id = args.length > 0 ? args[0] : "test-organization-id";
        String content = objectMapper.writeValueAsString(new OrganizationDto.Request(id));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, OrganizationDto.Response.class);
    }

    // get organization
    public static OrganizationDto.Response getOrganization(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String id = args.length > 0 ? args[0] : "test-organization-id";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/organization/" + id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        return objectMapper.readValue(resultString, OrganizationDto.Response.class);
    }

    // add member
    public static MemberDto.Response addMember(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String id = args.length > 0 ? args[0] : "test-member-id";

        MemberDto.SignupRequest dto = new MemberDto.SignupRequest(id, "test-password");
        String member = objectMapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, MemberDto.Response.class);
    }

    // login
    public static MemberDto.SignInResponse signin(MockMvc mockMvc, ObjectMapper objectMapper, String id, String password, ResultMatcher expect) throws Exception {
        if(expect == null){
            expect = MockMvcResultMatchers.status().isOk();
        }
        MemberDto.SignInRequest dto = new MemberDto.SignInRequest(id, password);
        String member = objectMapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/signin")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(expect)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, MemberDto.SignInResponse.class);
    }


    // get member
    public static MemberDto.Response getMember(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String id = args.length > 0 ? args[0] : "test-member-id";

        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String strResult = mResult.getResponse().getContentAsString();
        return objectMapper.readValue(strResult, MemberDto.Response.class);
    }

    // set member's organization
    public static MemberDto.Response setMemberOrganization(MockMvc mockMvc, ObjectMapper objectMapper, String target, String organizationId) throws Exception {
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/member?member=" + target + "&organization=" + organizationId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String mContent = mResult.getResponse().getContentAsString();
        return objectMapper.readValue(mContent, MemberDto.Response.class);
    }

    // add category
    public static CategoryDto.Response addCategory(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String name = args.length > 0 ? args[0] : "test-category-name";
        String content = objectMapper.writeValueAsString(new CategoryDto.AddRequest(name, "test-organization-id"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, CategoryDto.Response.class);
    }

    // get category
    public static CategoryDto.Response getCategory(MockMvc mockMvc, ObjectMapper objectMapper, String... args) throws Exception {
        String id = args.length > 0 ? args[0] : "1";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, CategoryDto.Response.class);
    }

    // add board
    public static BoardDto.Response addBoard(MockMvc mockMvc, ObjectMapper objectMapper, String writerId, Long categoryId, String content) throws Exception {
        String reqString = objectMapper.writeValueAsString(new BoardDto.AddRequest(writerId, categoryId, content));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(reqString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, BoardDto.Response.class);
    }

    // get board
    public static BoardDto.Response getBoard(MockMvc mockMvc, ObjectMapper objectMapper, String boardId) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/" + boardId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, BoardDto.Response.class);
    }

    // add comment
    public static CommentDto.Response addComment(MockMvc mockMvc, ObjectMapper objectMapper,String writerId, Long boardId, String commentContent) throws Exception{
        String content = objectMapper.writeValueAsString(new CommentDto.AddRequest("test-member-id", 2L, "comment-content"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        return objectMapper.readValue(resultString, CommentDto.Response.class);
    }

}
