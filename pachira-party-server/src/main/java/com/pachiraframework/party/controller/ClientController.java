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
import com.pachiraframework.party.entity.Client;
import com.pachiraframework.party.service.ClientService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 接入客户端应用相关的controller方法
 * @author KevinWang
 *
 */
@RestController
@RequestMapping("/api/party/")
public class ClientController extends AbstractPartyController {
	@Autowired
	private ClientService clientService;
	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ID获取客户端信息", notes = "根据url的id来获取客户端详细信息")
	@ApiImplicitParam(name = "clientId", value = "客户端ID", required = true, dataType = "Long")
	public ResponseEntity<ExecuteResult<Client>> getUser(@PathVariable("clientId") Long clientId) {
		return Optional.ofNullable(clientService.get(clientId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
