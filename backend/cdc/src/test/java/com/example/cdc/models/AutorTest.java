package com.example.cdc.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.metadata.BeanDescriptor;
import jakarta.validation.metadata.PropertyDescriptor;

public class AutorTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void deve_validar_instante() {
		
		// não pode ser nulo
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Autor.class);
		PropertyDescriptor propertyDescriptor = beanDescriptor.getConstraintsForProperty("instante");

		assertNotNull(propertyDescriptor);

		boolean temRestricaoNotNull = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(NotNull.class));
		assertTrue(temRestricaoNotNull);
		
	}
	
	@Test
	public void deve_validar_email() {
		
		// email é obrigatório, logo:
		
		// 1. não pode ser nulo e não pode estar vazio
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Autor.class);
		PropertyDescriptor propertyDescriptor = beanDescriptor.getConstraintsForProperty("email");

		assertNotNull(propertyDescriptor);

		boolean temRestricaoNotEmpty = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(NotEmpty.class));
		assertTrue(temRestricaoNotEmpty);
		
		// 2. deve ter formato válido
		boolean temRestricaoEmail = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(Email.class));
		assertTrue(temRestricaoEmail);
		
		String formatoValido = propertyDescriptor.getConstraintDescriptors()
        	.stream()
        	.filter(descriptor -> descriptor.getAnnotation().annotationType().equals(Email.class))
        	.findFirst()
        	.get()
        	.getAttributes()
        	.get("regexp")
        	.toString();
		assertTrue(formatoValido.equals(Autor.EMAIL_REGEX));

		
		// 3. deve validar regex do email

		// quando o e-mail é válido
		Autor autor = new Autor();
		autor.setEmail("teste@teste.com");
		assertTrue(autor.getEmail().matches(Autor.EMAIL_REGEX));

		// quando o e-mail é inválido
		autor.setEmail("teste.com");
		assertFalse(autor.getEmail().matches(Autor.EMAIL_REGEX));
		
	}
	
	
	@Test
	public void deve_validar_nome() {
		
		// nome é obrigatório, logo:
		
		// 1. não pode ser nulo e não pode estar vazio
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Autor.class);
		PropertyDescriptor propertyDescriptor = beanDescriptor.getConstraintsForProperty("nome");

		assertNotNull(propertyDescriptor);

		boolean temRestricaoNotEmpty = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(NotEmpty.class));
		assertTrue(temRestricaoNotEmpty);
		
	}
	
	@Test
	public void deve_validar_descricao() {
		
		// descrição é obrigatória, logo:
		
		// 1. não pode ser nulo e não pode estar vazio
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Autor.class);
		PropertyDescriptor propertyDescriptor = beanDescriptor.getConstraintsForProperty("descricao");

		assertNotNull(propertyDescriptor);

		boolean temRestricaoNotEmpty = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(NotEmpty.class));
		assertTrue(temRestricaoNotEmpty);
		
		
		// 2. pode conter no máximo 400 caracteres
		boolean temRestricaoSize = propertyDescriptor.getConstraintDescriptors()
				.stream()
				.anyMatch(constraintDescriptor -> constraintDescriptor.getAnnotation().annotationType().equals(Size.class));
		assertTrue(temRestricaoSize);
		
		String tamanhoMaximo = propertyDescriptor.getConstraintDescriptors()
	        	.stream()
	        	.filter(descriptor -> descriptor.getAnnotation().annotationType().equals(Size.class))
	        	.findFirst()
	        	.get()
	        	.getAttributes()
	        	.get("max")
	        	.toString();
		assertTrue(tamanhoMaximo.equals("500"));
		
	}
	
}
