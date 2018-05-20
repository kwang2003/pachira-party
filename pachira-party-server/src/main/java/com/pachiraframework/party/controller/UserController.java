package com.pachiraframework.party.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.User;
import com.pachiraframework.party.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关方法
 * 
 * @author KevinWang
 *
 */
@RestController
@RequestMapping("/api/party/")
public class UserController extends AbstractPartyController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ID获取用户信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
	public ResponseEntity<ExecuteResult<User>> getUser(@PathVariable("userId") Long userId) {
		return Optional.ofNullable(userService.get(userId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
