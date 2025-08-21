//package business.ViewRoom;
//
//import business.Room;
//
//public class ViewRoomUsecase {
//    private ListViewDAO listViewDAO;
//
//
//    public ViewRoomUseCase(ViewRoomDAO roomViewDAO) {
//        super();
////        this.listViewDAO = listViewDAO;
//    }
//
//    public List<StudentViewDTO> execute() throws SQLException, ParseException {
//        List<RoomtDTO> listDTO = null;
//        List<Room> students = null;
//        listDTO = listViewDAO.getAll();
//
//        //convert StudentDTO => Student
//        students = convertToBusinessObjects(listDTO);
//        //convert students business to StudentViewModel
//        return this.convertToViewDTO(students);
//    }
//
//    private List<Student> convertToBusinessObjects(List<StudentDTO> dtos) {
//        List<Student> students = new ArrayList<>();
//        for (StudentDTO dto : dtos) {
//            Student student = StudentFactory.createStudent(dto);
//            students.add(student);
//        }
//        return students;
//    }
//
//    private List<StudentViewDTO> convertToViewDTO(List<Student> students) {
//        List<StudentViewDTO> itemList = new ArrayList<StudentViewDTO>();
//        for (Student student : students) {
//            StudentViewDTO dto = new StudentViewDTO();
//            dto.id = student.getId();
//            dto.name = student.getName();
//            dto.birthDate = student.getBirthDate();
//            dto.major = student.getMajor();
//            dto.gpa = student.calculateGPA();
//            dto.academicRank = student.classifyAcademic();
//            itemList.add(dto);
//        }
//
//        return itemList;
//
//    }
//
//
//
//
//}
//}
