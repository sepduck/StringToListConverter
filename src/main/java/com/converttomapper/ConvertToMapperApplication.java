package com.converttomapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConvertToMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertToMapperApplication.class, args);
    }

}
//    public List<Long> getUidAsList(){
//        if (uid == null || uid.isEmpty()){
//            return List.of();
//        }
//        return Arrays.stream(uid.replace("{", "")
//                        .replace("}", "")
//                        .split(","))
//                .map(String::trim)
//                .map(Long::valueOf)
//                .collect(Collectors.toList());
//    }
//
//    public void setUidFromList(List<Long> userIds){
//        if (userIds == null || userIds.isEmpty()){
//            this.uid="{}";
//        }else {
//            this.uid = userIds.stream()
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(",", "{", "}"));
//        }
//    }
//public List<Long> getUidList() {
//    // Loại bỏ dấu ngoặc nhọn và tách chuỗi theo dấu phẩy
//    return Arrays.stream(uid.replaceAll("[{}]", "").split(","))
//            .map(Long::parseLong)  // Chuyển đổi từng phần tử thành Long
//            .collect(Collectors.toList());
//}
//
//    // Hàm để set chuỗi UID từ mảng
//    public void setUidList(List<Long> uidList) {
//        this.uid = uidList.stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining(",", "{", "}"));
//    }
//}


//    @PutMapping("/{groupId}/add-users")
//    public Groups addUserIdsToGroup(@PathVariable Long groupId, @RequestBody List<Long> userIds) {
//        return groupService.addUserIdsToGroup(groupId, userIds);
//    }
//
//    @PutMapping("/{groupId}/remove-user")
//    public Groups removeUserIdFromGroup(@PathVariable Long groupId, @RequestBody Long userId) {
//        return groupService.removeUserIdsFromGroup(groupId, userId);
//    }