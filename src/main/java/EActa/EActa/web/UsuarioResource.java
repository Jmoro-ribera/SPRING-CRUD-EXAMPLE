package EActa.EActa.web;

import EActa.EActa.modelo.Usuario;

import EActa.EActa.repositorio.UsuarioRepo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        return ResponseEntity.ok(usuarios);
    }
	
	
	/*
	 @GetMapping("/roles/{rol_id}") 
	 public ResponseEntity<List<Usuario>> getUsuariosByRol() { 
		 return ResponseEntity.ok(usuarioRepo.findAll(E)); 
	 }
	*/
	 
	
	@GetMapping("/{id}")
    public ResponseEntity<Usuario> getByUsuarioId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioRepo.findById(id).orElse(null));
    }
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Usuario>> getByUsuarioByName(@PathVariable String name) {
        return ResponseEntity.ok(usuarioRepo.findUsersByName(name));
    }
	
	@PostMapping
    public void save(@RequestBody @Valid Usuario usuario) {
        usuarioRepo.save(usuario);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateEmployee(@PathVariable(value = "id") Long userId,
	  @Valid @RequestBody Usuario newUser) throws ResourceNotFoundException {
	 Usuario oldUser = usuarioRepo.findById(userId)
	 .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
	 oldUser.setNombre(newUser.getNombre());
	 oldUser.setApellido(newUser.getApellido());
	 oldUser.setEmail(newUser.getEmail());
	 oldUser.setIdRol(newUser.getIdRol());
	 final Usuario updatedUser = usuarioRepo.save(oldUser);
	 return ResponseEntity.ok(updatedUser);
	}

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        usuarioRepo.deleteById(id);
    }
	
	
}
