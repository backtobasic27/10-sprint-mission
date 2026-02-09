package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.dto.response.UserResponse;
import com.sprint.mission.discodeit.dto.response.UserStatusResponse;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserStatusService userStatusService;

    // 사용자 등록
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        User user = userService.create(request, Optional.empty());
        UserDto userDto = userService.find(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(userDto));
    }

    // 모든 사용자 조회
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>>getAllUsers() {
        List<UserDto> users = userService.findAll();
        List<UserResponse> responses = users.stream()
                .map(UserResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // 특정 사용자 조회
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id")UUID id){
        UserDto userDto = userService.find(id);
        return ResponseEntity.ok(UserResponse.from(userDto));
    }

    // 사용자 정보 수정
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 사용자 온라인 상태 업데이트
    @RequestMapping(value = "/{id}/status", method = RequestMethod.PATCH)
    public ResponseEntity<UserResponse> updateUserStatus(@PathVariable("id") UUID id){
        // 현재 시간으로 lastActiveAt 업데이트
        UserStatusUpdateRequest statusRequest = new UserStatusUpdateRequest(Instant.now());
        userStatusService.updateByUserId(id,statusRequest);

        UserDto userDto = userService.find(id);
        return ResponseEntity.ok(UserResponse.from(userDto));
    }
}
