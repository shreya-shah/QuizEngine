//package com.stackroute.quizengine.service;
//
//import com.stackroute.quizengine.repository.QuizSessionRepo;
//import org.mockito.Mock;
//
//public class QuizEngineServiceTest {
//
//    QuizEngineService quizEngineService;
//
//    @Mock
//
//
//}









//
//public class QuizServiceTest {
//    Quiz quiz;
//
//    //Create a mock for UserRepository
//    @Mock//test double
//            QuizRepo quizRepo;
//
//    //Inject the mocks as dependencies into UserServiceImpl
//    @InjectMocks
//    QuizServiceImpl quizService;
//    List<Quiz> list = null;
//    Optional optional;
//
//    // private transient Optional<Quiz> options;
//    public int page = 0;
//    public int size = 5;
//    Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "quizId"));
//
//    @Before
//    public void setUp() {
//
//        MockitoAnnotations.initMocks(this);
//        quiz = new Quiz();
//        List<String> collaborators = new ArrayList<>();
//        List<String> concepts = new ArrayList<>();
//        List<String> participants = new ArrayList<>();
//        ObjectId objectId = new ObjectId("5c0628e8136ec70001284cdd");
//        participants.add("abc@gmail.com");
//        participants.add("xyz@gmail.com");
//        concepts.add("collections");
//        concepts.add("spring boot");
//        List<String> skills = new ArrayList<>();
//        skills.add("frontend");
//        skills.add("backend");
//        //quiz.setQuizId("12345");
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
//    @Test
//    public void testSaveQuiz() throws QuizCreationException {
//
//        when(quizRepo.save(any())).thenReturn(quiz);
//        Quiz savedQuiz = quizService.saveQuiz(quiz);
//        assertEquals(quiz, savedQuiz);
//    }
//
//    @Test
//    public void testGetQuizByEmail() throws QuizNotFoundException {
//        this.quizRepo.save(quiz);
//        Sort sort = Sort.by(Sort.Direction.DESC, "quizId");
//        when(quizRepo.findByCreatorEmail(quiz.getCreatorEmail(), PageRequest.of(page, size, sort))).thenReturn(list);
//        List<Quiz> savedQuiz = quizService.getQuizByEmail("sanju@gmail.com", 0, 5);
//        assertEquals(list, savedQuiz);
//    }
//
//    @Test
//    public void testGetPopularQuizzes() throws QuizNotFoundException {
//        Sort sort1 = Sort.by(Sort.Direction.DESC, "noOfTimesTaken");
//        when(quizRepo.findByPopularity(PageRequest.of(page, size, sort1))).thenReturn(list);
//        List<Quiz> savedQuiz = quizService.getPopularQuizzes(0, 5);
//        assertEquals(list, savedQuiz);
//    }
////    @Test
////    public void testGetQuizByroleAndskills() {
////        List<String> skills=new ArrayList<>();
////        skills.add("frontend");
////        Sort sort1 = new Sort(new Sort.Order(Sort.Direction.DESC, "quizId"));
////        when(quizRepo.findByroleAndskills(quiz.getRole(),skills,new PageRequest(skip,size,sort1))).thenReturn(list);
////        List<Quiz> savedQuiz = this.quizService.getQuizByroleAndskills("fullstack",Arrays.asList("frontend"),1,5);
////        System.out.print("ghfdfj"+savedQuiz);
////        assertEquals(list,savedQuiz);
////
////    }
//
//
////    @Test
////    public void testGetAllQuiz(){
////        when(quizRepo.findByroleOrskillsOrconcepts("fullstack",null,null)).thenReturn(list);
////        List<Quiz> savedQuiz=quizService.getAllQuiz("fullstack",null,null);
////        assertEquals(list,savedQuiz);
////    }
////    @Test
////    public void testGetPopularQuiz(){
////        when(quizRepo.findBypopularity()).thenReturn(list);
////        List<Quiz> savedQuiz=quizService.getPopularQuizzes(0,5);
////        assertEquals(list,savedQuiz);
////    }
//
////    @Test
////    public void
//
//}