<link rel="stylesheet" type="text/css" href="css/mycss.css">
<div class="app-main__outer">
    <div class="app-main__inner">
        <div class="app-page-title">
            <div class="page-title-wrapper">
                <div class="page-title-heading">
                    <div>QUẢN LÍ KHÓA HỌC</div>
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="main-card mb-3 card">
                <div class="card-header">Danh sách khóa học
                </div>
                <div class="table-responsive">
                    <table class="align-middle mb-0 table table-borderless table-striped table-hover" id="tableList">
                        <thead>
                            <tr>
                                <th class="text-left pl-4">Tên khóa học</th>
                                <th class="text-center" width="20%">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $selCourse = $conn->query("SELECT * FROM course_tbl ORDER BY cou_id DESC ");
                            if ($selCourse->rowCount() > 0) {
                                while ($selCourseRow = $selCourse->fetch(PDO::FETCH_ASSOC)) { ?>
                                    <tr>
                                        <td class="pl-4">
                                            <?php echo $selCourseRow['cou_name']; ?>
                                        </td>
                                        <td class="text-center">
                                            <button type="button" data-toggle="modal" data-target="#updateCourse-<?php echo $selCourseRow['cou_id']; ?>" class="btn btn-primary btn-sm">Cập nhật</button>
                                            <button type="button" id="deleteCourse" data-id='<?php echo $selCourseRow['cou_id']; ?>' class="btn btn-danger btn-sm">Xóa</button>
                                        </td>
                                    </tr>

                                <?php }
                            } else { ?>
                                <tr>
                                    <td colspan="2">
                                        <h3 class="p-3">Không tìm thấy khóa học</h3>
                                    </td>
                                </tr>
                            <?php }
                            ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </div>
