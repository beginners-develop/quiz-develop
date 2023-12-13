<!-- Modal For Add Course -->
<div class="modal fade" id="modalForAddCourse" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form class="refreshFrm" id="addCourseFrm" method="post">
     <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Thêm khóa hoc</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="col-md-12">
          <div class="form-group">
            <label>Khóa học</label>
            <input type="" name="course_name" id="course_name" class="form-control" placeholder="Nhập khóa học" required="" autocomplete="off">
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-primary">Thêm</button>
      </div>
    </div>
   </form>
  </div>
</div>


<!-- Modal For Update Course -->
<div class="modal fade myModal" id="updateCourse-<?php echo $selCourseRow['cou_id']; ?>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog " role="document">
     <form class="refreshFrm" id="addCourseFrm" method="post" >
       <div class="modal-content myModal-content" >
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Update ( <?php echo $selCourseRow['cou_name']; ?> )</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="col-md-12">
            <div class="form-group">
              <label>Course</label>
              <input type="" name="course_name" id="course_name" class="form-control" value="<?php echo $selCourseRow['cou_name']; ?>">
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Update Now</button>
        </div>
      </div>
     </form>
    </div>
  </div>


<!-- Modal For Add Exam -->
<div class="modal fade" id="modalForExam" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form class="refreshFrm" id="addExamFrm" method="post">
     <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Thêm bài kiểm tra</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="col-md-12">
          <div class="form-group">
            <label>Chọn khóa học</label>
            <select class="form-control" name="courseSelected">
              <option value="0">Chọn khóa học</option>
              <?php 
                $selCourse = $conn->query("SELECT * FROM course_tbl ORDER BY cou_id DESC");
                if($selCourse->rowCount() > 0)
                {
                  while ($selCourseRow = $selCourse->fetch(PDO::FETCH_ASSOC)) { ?>
                     <option value="<?php echo $selCourseRow['cou_id']; ?>"><?php echo $selCourseRow['cou_name']; ?></option>
                  <?php }
                }
                else
                { ?>
                  <option value="0">Không tìm thấy khóa học</option>
                <?php }
               ?>
            </select>
          </div>

          <div class="form-group">
            <label>Thời gian làm bài</label>
            <select class="form-control" name="timeLimit" required="">
              <option value="0">Chọn thời gian</option>
              <option value="10">10 phút</option> 
              <option value="20">20 phút</option> 
              <option value="30">30 phút</option> 
              <option value="40">40 phút</option> 
              <option value="50">50 phút</option> 
              <option value="60">60 phút</option> 
            </select>
          </div>

          <div class="form-group">
            <label>Số lượng câu hỏi</label>
            <input type="number" name="examQuestDipLimit" id="" class="form-control" placeholder="Nhập số lần làm bài">
          </div>

          <div class="form-group">
            <label>Tên bài kiểm tra</label>
            <input type="" name="examTitle" class="form-control" placeholder="Nhập tên bài kiểm tra" required="">
          </div>

          <div class="form-group">
            <label>Mô tả</label>
            <textarea name="examDesc" class="form-control" rows="4" placeholder="Nhập mô tả" required=""></textarea>
          </div>


        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-primary">Thêm bài kiểm tra</button>
      </div>
    </div>
   </form>
  </div>
</div>


<!-- Modal For Add Examinee -->
<div class="modal fade" id="modalForAddExaminee" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form class="refreshFrm" id="addExamineeFrm" method="post">
     <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Thêm học viên</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="col-md-12">
          <div class="form-group">
            <label>Họ và tên</label>
            <input type="" name="fullname" id="fullname" class="form-control" placeholder="Input Fullname" autocomplete="off" required="">
          </div>
          <div class="form-group">
            <label>Ngày sinh</label>
            <input type="date" name="bdate" id="bdate" class="form-control" placeholder="Input Birhdate" autocomplete="off" >
          </div>
          <div class="form-group">
            <label>Giới tính</label>
            <select class="form-control" name="gender" id="gender">
              <option value="0">Chọn giới tính</option>
              <option value="male">Nam</option>
              <option value="female">Nữ</option>
            </select>
          </div>
          <div class="form-group">
            <label>Khóa học</label>
            <select class="form-control" name="course" id="course">
              <option value="0">Chọn khóa học</option>
              <?php 
                $selCourse = $conn->query("SELECT * FROM course_tbl ORDER BY cou_id asc");
                while ($selCourseRow = $selCourse->fetch(PDO::FETCH_ASSOC)) { ?>
                  <option value="<?php echo $selCourseRow['cou_id']; ?>"><?php echo $selCourseRow['cou_name']; ?></option>
                <?php }
               ?>
            </select>
          </div>
          <div class="form-group">
            <label>Trình độ</label>
            <select class="form-control" name="year_level" id="year_level">
              <option value="0">Chọn Trình độ</option>
              <option value="first year">Năm nhất</option>
              <option value="second year">Năm hai</option>
              <option value="third year">Năm ba</option>
              <option value="fourth year">Năm bốn</option>
            </select>
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" id="email" class="form-control" placeholder="Nhập Email" autocomplete="off" required="">
          </div>
          <div class="form-group">
            <label>Mật khẩu</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Nhập mật khẩu" autocomplete="off" required="">
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-primary">Thêm</button>
      </div>
    </div>
   </form>
  </div>
</div>



<!-- Modal For Add Question -->
<div class="modal fade" id="modalForAddQuestion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form class="refreshFrm" id="addQuestionFrm" method="post">
     <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Thêm câu hỏi cho <br><?php echo $selExamRow['ex_title']; ?></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form class="refreshFrm" method="post" id="addQuestionFrm">
      <div class="modal-body">
        <div class="col-md-12">
          <div class="form-group">
            <label>Câu hỏi</label>
            <input type="hidden" name="examId" value="<?php echo $exId; ?>">
            <input type="" name="question" id="course_name" class="form-control" placeholder="Input question" autocomplete="off">
          </div>

          <fieldset>
            <legend>Danh sách câu trả lời</legend>
            <div class="form-group">
                <label>Đáp án A</label>
                <input type="" name="choice_A" id="choice_A" class="form-control" placeholder="Nhập đáp án A" autocomplete="off">
            </div>

            <div class="form-group">
                <label>Đáp án B</label>
                <input type="" name="choice_B" id="choice_B" class="form-control" placeholder="Nhập đáp án B" autocomplete="off">
            </div>

            <div class="form-group">
                <label>Đáp án C</label>
                <input type="" name="choice_C" id="choice_C" class="form-control" placeholder="Nhập đáp án C" autocomplete="off">
            </div>

            <div class="form-group">
                <label>Đáp án D</label>
                <input type="" name="choice_D" id="choice_D" class="form-control" placeholder="Nhập đáp án D" autocomplete="off">
            </div>

            <div class="form-group">
                <label>Đáp án đúng</label>
                <input type="" name="correctAnswer" id="" class="form-control" placeholder="Nhập đáp án đúng" autocomplete="off">
            </div>
          </fieldset>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-primary">Thêm câu hỏi</button>
      </div>
      </form>
    </div>
   </form>
  </div>
</div>