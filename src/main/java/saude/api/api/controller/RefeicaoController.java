package saude.api.api.controller;

import saude.api.api.model.Refeicao;
import saude.api.api.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refeicao")
public class RefeicaoController {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @GetMapping("/listar")
    public String listarRefeicoes() {
        return "redirect:/crud";
    }

    @PostMapping("/salvar")
    public String salvarRefeicao(@ModelAttribute Refeicao refeicao) {
        refeicaoRepository.save(refeicao);
        return "redirect:/crud";
    }

    @GetMapping("/editar/{id}")
    public String editarRefeicao(@PathVariable Long id) {
        if (!refeicaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Registro de Refeicao não encontrado: " + id);
        }
        return "redirect:/crud";
    }

    @PostMapping("/editar/{id}")
    public String atualizarRefeicaoForm(@PathVariable Long id, @ModelAttribute Refeicao refeicaoAtualizada) {
        Refeicao refeicaoExistente = refeicaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Refeição não encontrada: " + id));

        // Atualiza os campos
        refeicaoExistente.setNome(refeicaoAtualizada.getNome());
        refeicaoExistente.setTipo(refeicaoAtualizada.getTipo());
        refeicaoExistente.setQuantidade(refeicaoAtualizada.getQuantidade());
        refeicaoExistente.setData(refeicaoAtualizada.getData());

        refeicaoRepository.save(refeicaoExistente);

        return "redirect:/crud";
    }

    @GetMapping("/excluir/{id}")
    public String excluirRefeicao(@PathVariable Long id) {
        refeicaoRepository.deleteById(id);
        return "redirect:/crud";
    }
}