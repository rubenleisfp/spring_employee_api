package com.fp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fp.dto.EmployeeDto;
import com.fp.mapper.EmployeeMapper;
import com.fp.model.Employee;
import com.fp.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * http://localhost:8080/v3/api-docs http://localhost:8080/swagger-ui/index.html
 * 
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))
            })
    })
    @GetMapping
    public List<EmployeeDto> findAll() {
        List<EmployeeDto> employeesDto = employeeService.findAll();
        return employeesDto;
    }

    @Operation(summary = "Get an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long employeeId) {
        Optional<EmployeeDto> employeeDto = employeeService.getById(employeeId);
        if (employeeDto.isPresent()){
            return ResponseEntity.ok().body(employeeDto);
        } else {
            return responseNotFound(employeeId);
        }
    }

    @Operation(summary = "Get all employees with %name%")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))
            })
    })
    @GetMapping(value = "search")
    public List<EmployeeDto> findByName(@RequestParam(name = "name") String name) {
        List<EmployeeDto> employeesDto = employeeService.findByName(name);
        return employeesDto;
    }

    @Operation(summary = "Create an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Data not valid", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto  employeeDtoSaved = employeeService.create(employeeDto);
        return new ResponseEntity<>(employeeDtoSaved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Data not valid", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long employeeId,
                                    @Valid @RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> employeeDtoUpdated = employeeService.update(employeeId, employeeDto);
        if (employeeDtoUpdated.isPresent()) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return responseNotFound(employeeId);
        }
    }

    @Operation(summary = "Delete an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long employeeId) {
        boolean deleted = employeeService.delete(employeeId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return responseNotFound(employeeId);
        }
    }

    private ResponseEntity<?> responseNotFound(Long employeeId) {
        String errorMessage = "Employee with id '" + employeeId + "' not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorMessage));
    }
}
