package EActa.EActa.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EActa.EActa.modelo.Rol;
import EActa.EActa.repositorio.RolRepo;


@RestController
@RequestMapping("/api/roles")
public class RolResource {
	
	@Autowired
	private RolRepo rolRepo;
	
	@GetMapping
	public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolRepo.findAll();
        return ResponseEntity.ok(roles);
    }
	
	
	/*
	 * @GetMapping("/roles/{rol_id}") public ResponseEntity<List<Usuario>>
	 * getUsuariosByRol() { return ResponseEntity.ok(usuarioRepo.findAll(W)); }
	 */
	 
	
	@GetMapping("/{id}")
    public ResponseEntity<Rol> getByRolId(@PathVariable Long id) {
        return ResponseEntity.ok(rolRepo.findById(id).orElse(null));
    }
	
	@PostMapping
    public void save(@RequestBody @Valid Rol rol) {
        rolRepo.save(rol);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        rolRepo.deleteById(id);
    }
	
	
}

