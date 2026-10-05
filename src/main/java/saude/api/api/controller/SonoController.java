package saude.api.api.controller;

import saude.api.api.model.Sono;
import saude.api.api.repository.SonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sono")
public class SonoController {

    @Autowired
    private SonoRepository sonoRepository;

    @GetMapping("/cadastro")
    public String formCadastro() {
        return "redirect:/crud";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Sono sono) {
        sonoRepository.save(sono);
        return "redirect:/crud";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id) {
        if (!sonoRepository.existsById(id)) {
            throw new IllegalArgumentException("Registro de Sono não encontrado: " + id);
        }
        return "redirect:/crud";
    }

    @PostMapping("/editar/{id}")
    public String atualizarSonoForm(@PathVariable Long id, @ModelAttribute Sono sonoAtualizado) {
        Sono sonoExistente = sonoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro de sono não encontrado: " + id));

        // Atualiza os campos
        sonoExistente.setHorasDormidas(sonoAtualizado.getHorasDormidas());
        sonoExistente.setQualidade(sonoAtualizado.getQualidade());
        sonoExistente.setData(sonoAtualizado.getData());

        sonoRepository.save(sonoExistente);

        return "redirect:/crud";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        sonoRepository.deleteById(id);
        return "redirect:/crud";
    }
}
