//package com.stackroute.quizengine.controller;
//
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//
//public class QuizEngineControllerTest {
//
//}
//
//
//









//package com.stackroute.quizcreationservice.controller;

//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class QuizControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Quiz quiz;
//    @MockBean
//    private QuizService quizService;
//    @InjectMocks
//    private QuizController quizController;
//
//    private List<Quiz> list = null;
//
//
//    @Before
//    public void setUp() {
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
//        quiz = new Quiz();
//        ObjectId objectId = new ObjectId("5c0628e8136ec70001284cdd");
//        List<String> collaborators = new ArrayList<>();
//        List<String> concepts = new ArrayList<>();
//        List<String> participants = new ArrayList<>();
//        participants.add("abc@gmail.com");
//        participants.add("xyz@gmail.com");
//        concepts.add("collections");
//        concepts.add("spring boot");
//        List<String> skills = new ArrayList<>();
//        skills.add("frontend");
//        skills.add("backend");
//        quiz.setQuizId("abc");
//        quiz.setTitle("Quiz1");
//        quiz.setDescription("quiz on java");
//        quiz.setRole("fullstack");
//        Section section = new Section();
//        section.setSectionName("coding");
//        section.setConcepts(concepts);
//        section.setSkills(skills);
//        section.setDifficultyLevel(null);
//        section.setMaxScore(30);
//        quiz.setTotalQuestions(30);
//        quiz.setTotalDuration(60);
//        quiz.setCreatorEmail("sanju@gmail.com");
//        quiz.setProficiencyLevel(null);
//        quiz.setAllowedToParticipate(participants);
//        quiz.setAssessmentDepth(2);
//        quiz.setCollaborators(collaborators);
//        Settings settings = new Settings();
//        settings.setAllowEdit(true);
//        settings.setAllowPause(true);
//        settings.setAllowRevisit(false);
//        settings.setShowHints(false);
//        quiz.setNoOfTimesTaken(10);
//        list = new ArrayList<>();
//        list.add(quiz);
//    }
//
//
//    @Test
//    public void testSaveQuiz() throws Exception {
//        when(quizService.saveQuiz(any())).thenReturn(quiz);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/quizzes")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quiz)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    public void testGetQuizByEmail() throws Exception {
//        when(quizService.getQuizByEmail("sanju@gmail.com", 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/sanju@gmail.com/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testGetPopularQuiz() throws Exception {
//        when(quizService.getPopularQuizzes(0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/popularQuiz")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testDeleteQuiz() throws Exception {
//        when(quizService.deleteQuiz(quiz.getQuizId())).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/quizzes/abc")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quiz)))
//                .andExpect(MockMvcResultMatchers.status().isAccepted())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testGetQuizzesByQueries() throws Exception {
//        List<String> concepts = new ArrayList<>();
//        concepts.add("collections");
//        concepts.add("objects");
//        List<String> skills = new ArrayList<>();
//        skills.add("is aware of datatypes");
//        when(quizService.getAllQuizzes()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getByRole(quiz.getRole(), 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?role=fullstack")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getByRoleAndConcepts(quiz.getRole(), concepts, 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?role=fullstck&concepts=[collections]")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getBySkillsAndConcepts(skills, concepts, 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?skills=is aware of datatypes&concepts=[collections,objects]")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getQuizByRoleAndSkills(quiz.getRole(), skills, 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?role=fullstck&skills=is aware of datatypes")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getByRoleAndSkillsAndConcepts(quiz.getRole(), skills, concepts, 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?role=fullstck&skills=is aware of datatypes&concepts=[collections,objects]")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        when(quizService.getBySkills(skills, 0, 5)).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quizzes/?skills=is aware of datatypes")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(list)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}