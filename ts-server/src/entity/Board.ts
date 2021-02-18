import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, OneToOne, JoinColumn, ManyToOne, OneToMany, ManyToMany } from 'typeorm';
import { Category } from './Category';
import { Comment } from './Comment';
import { Member } from './Member';

@Entity()
export class Board {
    @PrimaryGeneratedColumn()
    id!: number;

    @ManyToMany(() => Member, writer => writer.board)
    writer!: Member;

    @CreateDateColumn({ type: 'datetime' })
    regDate!: Date;

    @UpdateDateColumn({ type: 'datetime' })
    editDate!: Date;

    @Column({ type: 'varchar', length: 5000 })
    content!: string;

    @Column({ type: 'json', nullable: true })
    files!: JSON;

    @Column({ default: 0 })
    favorite!: number;

    @ManyToOne(() => Category, category => category.board)
    category!: Category;

    @OneToMany(() => Comment, comment => comment.board)
    comment!: Comment[];

}
