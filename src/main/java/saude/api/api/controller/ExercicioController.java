package saude.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import saude.api.api.model.Exercicio;
import saude.api.api.repository.ExercicioRepository;

@Slf4j
@Controller
@RequestMapping("/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping("/listar")
    public String listarExercicios() {
        return "redirect:/crud";
    }

    @PostMapping("/salvar")
    public String salvarExercicio(@ModelAttribute Exercicio exercicio) {
        exercicioRepository.save(exercicio);
        return "redirect:/crud";
    }

    @GetMapping("/editar/{id}")
    public String editarExercicio(@PathVariable Long id) {
        if (!exercicioRepository.existsById(id)) {
            throw new IllegalArgumentException("Registro de Exercicio não encontrado: " + id);
        }
        return "redirect:/crud";
    }

    @PostMapping("/editar/{id}")
    public String atualizarExercicio(@PathVariable Long id, @ModelAttribute Exercicio exercicioAtualizado) {
        try {
            Exercicio exercicioExistente = exercicioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Exercício não encontrado: " + id));

            // Atualiza os campos
            exercicioExistente.setNome(exercicioAtualizado.getNome());
            exercicioExistente.setSeries(exercicioAtualizado.getSeries());
            exercicioExistente.setRepeticoes(exercicioAtualizado.getRepeticoes());
            exercicioExistente.setCarga(exercicioAtualizado.getCarga());
            exercicioExistente.setTempo(exercicioAtualizado.getTempo());
            exercicioExistente.setData(exercicioAtualizado.getData());

            exercicioRepository.save(exercicioExistente);

            return "redirect:/crud";
        } catch (Exception e) {
            return "redirect:/crud";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirExercicio(@PathVariable Long id) {
        exercicioRepository.deleteById(id);
        return "redirect:/crud";
    }
}
